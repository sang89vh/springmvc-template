var myPage=0;
var jTable;
var jTableDataSource;


function deleteDocument(docid,jRow){
	
	BaasBox.deleteObject(docid, collectionName)
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
	$(".mbackend-document-delete").click(function(){
		var docId=$(this).attr("docid");
		var jRow=$(this).parents('tr') ;
		BootstrapDialog.confirm("Bạn có chắc chắn muốn xóa bản ghi:"+docId,function(confirm){
			
			console.log(confirm);
			if(confirm===true){
				deleteDocument(docId,jRow);
			}
			
			
		});
	});
}
$(document).ready(function(){
	$("#mbackend-create-document").click(function(){
		

		BootstrapDialog.show({
	        message: '<div class="form-group">'+
			'<label for="txtFileAttachedData">Dữ liệu (JSON data)</label>'+			
			'<textarea class="form-control" id="txtFileAttachedData" '+
			'placeholder="Dữ liệu  (JSON data)" required autofocus></textarea>'+
			'<p class="help-block">ví dụ:{"name":"my box"}.</p>'+
			'</div>',	     
	        buttons: [{
	            label: 'Hủy',
	            action: function(dialogRef) {
	                dialogRef.close();
	            }
	        },{
	            label: 'Lưu',
	            hotkey: 13,
	            cssClass: 'btn-primary',
	            action: function(dialogRef) {
	            	var myJson=$("#txtFileAttachedData").val();
		    		if(myJson!=""&&!IsJsonString(myJson)){
		    			alert("Dữ liệu (JSON data) sai định dạng");
		    			return false;
		    			
		    		}else{
		    			$(this).button("loading");
	                	// Assumes a collection named "posts" has been created
		    			var post=JSON.parse(myJson);
		    			post.applicationId=applicationId;
	                	BaasBox.save(post, collectionName)
	                	  .done(function(res) {
	                	    console.log("res ", res);
	                	   /* dialogRef.close();
		                	 return true;*/
		                	 location.reload();
	                	  })
	                	  .fail(function(error) {
	                	    console.log("error ", error);
	                	  })
	                	
	                }
	                
	            }
	        }]
	    });
		
	})
	BaasBox.loadCollectionWithParams(collectionName, {page: myPage, 
														recordsPerPage: BaasBox.pagelength,
														orderBy : "_creation_date desc"
	
	})
	  .done(function(res) {
		  console.log("res ", res);
		    jTableDataSource=res;
		    jTable=$('#mbackend-list-collection').DataTable( {
		    	 "info":     false,
		    	 "lengthChange": false,
		    	 "columnDefs": [
		    	               
		    	                
		    	                {
		    	                	"targets": [ 2 ],
		    	                	 "render": function ( data, type, row ) {
		    	                         return moment(new Date(data)).fromNow();
		    	                     }
		    	                },
		    	                {
		    	                	"targets": [ 3 ],
		    	                	"render": function ( data, type, row ) {
		    	                		return ' <button docid="'+ data+'"   class="btn btn-xs btn-danger fui-cross-circle mbackend-document-delete">&nbsp;Xóa</button>';
		    	                	}
		    	                },
		    	                {
		    	                	"targets": [ 4 ],
		    	                	"render": function ( data, type, row ) {
		    	                		return ' <a href="/collection/my-document/'+ data+'/'+collectionId+'/'+applicationId+'/'+collectionName+'"   class="btn btn-xs btn-primary fui-new">&nbsp;Chi tiết</a>';
		    	                	}
		    	                }
		    	               
		    	            ],
		        data: jTableDataSource,
		        "initComplete": function () {
		    		 bindingEventDeleteFile();
			        },
		        columns: [
		            { data: 'id' },
		            { data: '@version' },
		            { data: '_creation_date' },
		            { data: 'id' },
		            { data: 'id' }
		        ]
		    } );
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })	
})
