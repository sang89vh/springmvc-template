var jTable;
var jTableDataSource;
//init jtable with mycollection in application
function loadMyCollection(){
	
	    jTable=$('#mbackend-list-file').DataTable( {
	    	 "info":     false,
	    	 "scrollX": "1200px",
	    	 "lengthChange": false,
	    	 "columnDefs": [
	    	                
	    	                {
	    	                	"targets": [ 5 ],
	    	                	 "render": function ( data, type, row ) {
	    	                		 if(data===undefined){
	    	                			 data={};
	    	                		 }
	    	                         return JSON.stringify(data);
	    	                     }
	    	                },
	    	                {
	    	                	"targets": [ 7 ],
	    	                	 "render": function ( data, type, row ) {
	    	                		 
	    	                         return moment(new Date(data)).fromNow();
	    	                     }
	    	                },
	    	                {
	    	                	"targets": [ 0 ],
	    	                	"render": function ( data, type, row ) {
	    	                		var icon=getFileIcon(row.contentType,data);
	    	                		return icon;
	    	                	}
	    	                },
	    	                {
	    	                	"targets": [ 3 ],
	    	                	"render": function ( data, type, row ) {
	    	                		var fileSize=bytesToSize(row.contentLength);
	    	                		return fileSize;
	    	                	}
	    	                },
	    	                {
	    	                	"targets": [ 2 ],
	    	                	"render": function ( data, type, row ) {
	    	                		return "<a href='"+BaasBox.endPoint+"/file/" + row.id+ "?download=true&X-BB-SESSION="+BaasBox.getCurrentUser().token+"&X-BAASBOX-APPCODE="+ BaasBox.appcode+"' target='_new'>"+ data +"</a>";
	    	                	}
	    	                },
	    	                {
	    	                	"targets": [ 1 ],
	    	                	"render": function ( data, type, row ) {
	    	                		return '<button docid="'+data+'" fileName="'+row.fileName+'" class="btn  btn-xs btn-warning mbackend-btn-delete-file"><span class="fui-trash"></span>&nbsp;Xóa</button>';
	    	                	}
	    	                }
	    	               
	    	            ],
	    	 
	    	 data: jTableDataSource,
	    	 "initComplete": function () {
	    		 bindingEventDeleteFile();
		        },
	        columns: [
					{ "data": 'id' },//preview file
					{ "data": 'id' },//action delete
					{ "data": 'fileName' },//download
					{ "data": 'contentLength' },//length
					{ "data": 'id' },//id
					{ "data": 'attachedData' },//attachedData
					{ "data": 'contentType' },//type
					{ "data": '_creation_date' }//create date
					
					
					
	        ]
	    } );
	  
	  
}




function deleteFile(docid,jRow){
	
	BaasBox.deleteFile(docid)
	  .done(function(res) {
	    console.log("res ", res);
	    jTable.row( jRow)
        .remove()
        .draw();
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
}
function bindingEventDeleteFile(){
	$(".mbackend-btn-delete-file").click(function(){
		var fileName=$(this).attr("fileName");
		var docId=$(this).attr("docid");
		var jRow=$(this).parents('tr') ;
		BootstrapDialog.confirm("Bạn có chắc chắn muốn xóa file:"+fileName,function(confirm){
			
			console.log(confirm);
			if(confirm===true){
				deleteFile(docId,jRow);
			}
			
			
		});
	});
}

$(document).ready(function(){
	
	
	$("#mbackend-create-collection").click(function(){
		
		BootstrapDialog.show({
			title: 'Upload file',
		    message: '<form class="uploadForm" >'+
				'<div class="form-group">'+
				'<label for="txtFileAttachedData">Dữ liệu đính kèm (JSON data)</label>'+			
				'<textarea class="form-control" id="txtFileAttachedData" name="attachedData"'+
				'placeholder="Dữ liệu đính kèm (JSON data)" ></textarea>'+
				'<p class="help-block">ví dụ:{"name":"my box"}.</p>'+
				'</div>'+		
				'<div class="form-group">'+
					'<label for="fuFile">File input</label> <input type="file" name="file"'+
							'id="fuFile">'+
					'<p class="help-block">Chọn File dữ liệu.</p>'+
				'</div>'+
				'<button type="button" class="btn btn-warning" id="mbackend-btn-cancel-upload" style="margin-right: 10px">Hủy bỏ</button>'+
				'<input type="submit" id="mbackend-btn-submit-upload" class="btn btn-primary" value="Lưu"/>'+
				'</form>',
		    onshown: function(dialogRef){
		    	$(".uploadForm").submit(function(e) {
		    		var myJson=$("#txtFileAttachedData").val();
		    		if(myJson!=""&&!IsJsonString(myJson)){
		    			alert("Dữ liệu đính kèm (JSON data) sai định dạng");
		    			return false;
		    			
		    		}
		    		  $("#mbackend-btn-submit-upload").button("loading");
					  console.log("form submit click");
					  e.preventDefault();
					  var formObj = $(this);
					  var formData = new FormData(this);
					  BaasBox.uploadFile(formData)
					    .done(function(res) {
					      console.log("res ", res);
//					      BootstrapDialog.closeAll()
					      location.reload();
					    })
					    .fail(function(error) {
					     $("#mbackend-btn-submit-upload").button("reset");
					     alert(error.message);
					      console.log("error ", error);
					    })
					});
		    	$("#mbackend-btn-cancel-upload").click(function(){
		    		BootstrapDialog.closeAll();
		    	})
            },
		    
		});
		
		
		
	});

	
	$.ajax({
		  url: BaasBox.endPoint+"/file/details?X-BB-SESSION=" + BaasBox.getCurrentUser().token,
		  type: "GET",
		}).done(function(results) {
			jTableDataSource=results.data;
			loadMyCollection();
		 
		}).fail(function(error) {
			console.log(error.http_code)
			if(error.http_code===500||error.result==="error"){
				BootstrapDialog.alert(error.message);
			}
		 
		}).error(function(error) {
			console.log(error.http_code)
			if(error.http_code===500||error.result==="error"){
				BootstrapDialog.alert(error.message);
			}
		 
		});
	  
	  
})