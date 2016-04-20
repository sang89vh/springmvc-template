var myPage=0;
var jTable;
var jTableDataSource;
var collectionName='MOVIES';

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
		var TITLE=$(this).attr("TITLE");
		var jRow=$(this).parents('tr') ;
		BootstrapDialog.confirm("Bạn có chắc chắn muốn xóa video: "+TITLE,function(confirm){
			
			console.log(confirm);
			if(confirm===true){
				deleteDocument(docId,jRow);
			}
			
			
		});
	});
}
$(document).ready(function(){
	$("#mbackend-create-document").click(function(){
		
		location.href="/videos/create/";		
		
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
		    	                	"targets": [ 0 ],
		    	                	 "render": function ( data, type, row ) {
		    	                         return '<iframe width="200" height="113" src="https://www.youtube.com/embed/'+data+'" frameborder="0" allowfullscreen></iframe>';;
		    	                     }
		    	                },
		    	                
		    	                {
		    	                	"targets": [ 2 ],
		    	                	"render": function ( data, type, row ) {
		    	                		return ' <button docid="'+ data+'"  TITLE="'+ row.TITLE+'" class="btn btn-xs btn-danger fui-cross-circle mbackend-document-delete">&nbsp;Xóa</button>';
		    	                	}
		    	                },
		    	                {
		    	                	"targets": [ 3 ],
		    	                	"render": function ( data, type, row ) {
		    	                		return ' <a href="/videos/detail/'+ row.id+'"   class="btn btn-xs btn-primary fui-new">&nbsp;Chi tiết</a>';
		    	                	}
		    	                }
		    	               
		    	            ],
		        data: jTableDataSource,
		        "initComplete": function () {
		    		 bindingEventDeleteFile();
			        },
		        columns: [
		            { data: 'YOUTUBE' },
		            { data: 'TITLE' },
		            { data: 'id' },
		            { data: 'id' }
		        ]
		    } );
	  })
	  .fail(function(error) {
	    console.log("error ", error);
	  })	
})
