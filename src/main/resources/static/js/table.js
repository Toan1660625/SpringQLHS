$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault();
    	$("#listJava").css("display", "none");
     	$("#pageJava").css("display", "none");
//    	alert("Hello! I am an alert box!!");
//    	ajax_find_submit();
    
//     	var  search = { "studentName": "6"};
     	
/*     	var search = JSON.stringify({
     	    studentName: $("#studentName").val()
     	});
     	*/
     	
        var search = {}
        search["studentName"] = $("#studentName").val();
     	$("#btn-search").prop("disabled", true);
     	
//        var studentName = $("#studentName").val();
     	
     	
	    $.ajax({
				   type: "GET",
			       url: "/api/search",
			       contentType: "application/json; charset=utf-8",
			       data: JSON.stringify({search}),
			       dataType: 'json',
			       cache: false,
			       timeout: 600000,
				   success: function (result) {
			 	        console.log('AJAX success: ' + result);
				        var tbhtml = "<div>kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + result.totalPage+ "</div>";
				        $('#feedback').html(tbhtml);
				        $("#btn-search").prop("disabled", false);
				    },
				    error: function (e) {
//				    	  console.log('AJAX ERRORRRRRRRRRRRRR: ');
				        console.log('AJAX error:' + e);
//				        $("#btn-search").prop("disabled", false);
				    }
		});

     	
     	
    });

});


function ajax_find_submit(){
	var search = {}
    search["studentName"] = $("#studentName").val();
	
	 $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "/api/search",
	        data: JSON.stringify(search),
	        dataType: 'json',
	        cache: false,
	        timeout: 600000,
	        success: function (result) {

	            var tbhtml = "<div class='alert alert-warning alert-dismissible fade show col-sm-8 offset-md-2'>";
	            tbhtml += "<strong>Warning!"+"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + result.totalPage+" </strong>"+obj.getMessage;
	            tbhtml += '</div>';

	        	$('#feedback').html(tbhtml);
//	        	var tbhtml  = '<h4 class="text-center">Search reuslt</h4>'
//			        		 + "<p class='text-center'>There are "+result.totalStudent+" students, divided into "+result.totalPage+" pages</p>"
//			        		 + "<table class='table table-striped table-bordered text-center'>"
//			        		 + "<tr>"  
//			    			 + "<th>No</th>"  
//							 + "<th>Name</th>"  
//							 + "<th>Code</th>"
//							 + "<th>Address</th>"
//							 + "<th>Birthday</th>"  
//							 + "<th>Score</th>"  
//							 + "<th>Edit</th>" 
//							 + "</tr>";  
//		            for(var i = 0; i < result.data.length; i++) {
//		            	var j = i + 1 ;
//		            	tbhtml += "<tr>"
//		                 + "<td>"+j+"</td>"
//		                 + "<td>"+result.data[i].studentName+"</td>"
//		                 + "<td>"+result.data[i].studentCode+"</td>"
//		                 + "<td>"+result.data[i].studentInfo.address+"</td>"
//		                 + "<td>"+result.data[i].studentInfo.getDateOfBirthToString+"</td>"
//		                 + "<td>"+result.data[i].studentInfo.averageSore+"</td>"
//		                 + "<td>"
//		                 + "<form action='/deleteStudent/id/"+data.result[i].studentInfo.infoId+"' onsubmit='return submitForm(this);'>"
//		                 + "<a href='/infoStudent/id/"+data.result[i].studentId+"' class='btn btn-primary'>Edit</a>&nbsp"
//	                	 + "<button class='btn btn-danger' >Delete</button></form></td>"
//		                 + "</tr>";
//		            }
//		            tbhtml += "<input type='hidden' id='totalPage' value="+result.totalPage+">";
//		            var json = "<h4>Ajax Response</h4><pre>"
//		                + JSON.stringify(result, null, 4) + "</pre>";
//		            
//		            if(result.data.length > 0){
//		            	$('#feedback').html(tbhtml);
//		            	document.getElementById("pagination").style.display = 'block';
//		            }else{
//		            	var error = "<div class='alert alert-warning alert-dismissible fade show col-sm-8 offset-md-2'>";
//		            	error += "<strong>Warning!</strong> "+result.getMessage+"</div>";
//		            	$('#feedback').html(error);
//		            }
//	            
//	            console.log("SUCCESS : ", result);
//	            $("#btn-search").prop("disabled", false);

	        },
	        error: function (e) {
//	        	var obj = JSON.parse(e.responseText);
//	            var json = "<h4>Search reuslt warning</h4><pre>"
//	                + obj.getMessage + "</pre>";
	            var error = "<div class='alert alert-warning alert-dismissible fade show col-sm-8 offset-md-2'>";
	        	error += "<strong>Warning! </strong>"+obj.getMessage;
	        	error += '</div>';
	        	$('#feedback').html(error);

	            console.log("ERROR : ", e);
//	            $("#btn-search").prop("disabled", false);
//
	        }
	    });
}