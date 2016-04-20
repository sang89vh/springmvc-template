var jTable;
var jTableDataSource;
//init jtable with mycollection in application
function loadMyCollection(){
	BaasBox.loadCollectionWithParams("MBACKEND_COLLECTION",{where:"applicationId='"+applicationId+"'"})
	  .done(function(res) {
	    console.log("res ", res);
	    jTableDataSource=res;
	    jTable=$('#mbackend-list-application').DataTable( {
	    	 "info":     false,
	    	 "lengthChange": false,
	    	 "columnDefs": [
	    	                {
	    	                    "targets": [ 0 ],
	    	                    "visible": false,
	    	                },
	    	                {
	    	                    "targets": [ 1 ],
	    	                    "visible": false,
	    	                },
	    	                {
	    	                	"targets": [ 3 ],
	    	                	 "render": function ( data, type, row ) {
	    	                		 
	    	                         return ' <span id="'+ data+'"  >0</span>';
	    	                     }
	    	                },
	    	                {
	    	                	"targets": [ 4 ],
	    	                	 "render": function ( data, type, row ) {
	    	                         return ' <button applicationId="'+ data+'"   class="btn btn-xs btn-danger fui-cross-circle mbackend-application-delete">&nbsp;Xóa</button>';
	    	                     }
	    	                },
	    	                {
	    	                	"targets": [ 5 ],
	    	                	"render": function ( data, type, row ) {
	    	                		return ' <a href="/collection/my-collection/'+ data+'/'+applicationId+'/'+row.collectionName+'"   class="btn btn-xs btn-primary fui-new">&nbsp;Chi tiết</a>';
	    	                	}
	    	                }
	    	               
	    	            ],
	        data: jTableDataSource,
	        "initComplete": function () {
				$.each( jTableDataSource, function( i, val ) {
					   countTotalDocument(val.collectionName);
					});
	        },
	        columns: [
	            { data: '@rid' },
	            { data: 'id' },
	            { data: 'collectionName' },
	            { data: 'collectionName' },
	            { data: 'id' },
	            { data: 'id' }
	        ]
	    } );
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
	  
}
function updateDate(){
	
	BaasBox.loadCollectionWithParams("MBACKEND_COLLECTION",{where:"applicationId='"+applicationId+"'"})
	  .done(function(res) {
	    console.log("res ", res);
	    jTableDataSource=res;
	        var oldData=jTable.data();
			var newData=jTableDataSource.slice(oldData.length, jTableDataSource.length);
			jTable.rows.add(newData)
		    .draw();
			   
			
			
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
}

function countTotalDocument(documentName){
	console.log(documentName);
	BaasBox.fetchObjectsCount(documentName)
	  .done(function(res) {
		var count=res['data']['count'];
	    console.log("res ",count );
	    $("#"+documentName).text(count);
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })
}
$(document).ready(function(){
	
	
	  loadMyCollection();
	  
	  $("#mbackend-create-collection").click(function(){
			BootstrapDialog.show({
		        message: 'Tên bảng dữ liệu: <input type="text" class="form-control" placeholder="Tên bảng dữ liệu" required autofocus />',       
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
		            	var collectionName = dialogRef.getModalBody().find('input').val();
		                if($.trim(collectionName.toLowerCase()) == '') {
		                    alert('Tên bảng dữ liệu của bạn!');
		                    return false;
		                }else{
		                	var url="/collection/create";
		                	console.log(collectionName);
		                	var jsonPostData ={ 
		                			applicationId:applicationId,
		                			collectionName :collectionName,
		                			userName : BaasBox.getCurrentUser().username
		                						}   
		                	$.ajax({
		          			  url: url,
		          			  type: "POST",
		          			  contentType: "application/json; charset=utf-8",
		          			  dataType: "json",
		          			  data:  JSON.stringify(jsonPostData)
		          			}).done(function(baasboxCreateCollectionOutVO) {
		          				console.log(baasboxCreateCollectionOutVO.http_code)
		          				if(baasboxCreateCollectionOutVO.result==="error"){
		          					BootstrapDialog.alert(baasboxCreateCollectionOutVO.message);
		          				}else{
		          					//location.reload();
		          					updateDate();
		          					
		          				}
		          			 
		          			}).fail(function(baasboxCreateCollectionOutVO) {
		          				console.log(baasboxCreateCollectionOutVO.http_code)
		          				//baasbox login
		          				if(baasboxCreateCollectionOutVO.http_code===500||baasboxCreateCollectionOutVO.result==="error"){
		          					BootstrapDialog.alert(baasboxCreateCollectionOutVO.message);
		          				}
		          			 
		          			}).error(function(baasboxCreateCollectionOutVO) {
		          				console.log(baasboxCreateCollectionOutVO.http_code)
		          				//baasbox login
		          				if(baasboxCreateCollectionOutVO.http_code===500||baasboxCreateCollectionOutVO.result==="error"){
		          					BootstrapDialog.alert(baasboxCreateCollectionOutVO.message);
		          				}
		          			 
		          			});
		                	 dialogRef.close();
		                	 return true;
		                }
		                
		            }
		        }]
		    });
			});
})