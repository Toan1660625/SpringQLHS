$(document).ready(function() {
	$("#search-form").submit(function(event) {
		event.preventDefault();
		$("#pageJava").css("display", "none");
		$("#dataList").empty();
		ajax_find_submit();

	});

});

var totalPage;
var page = 0;
var url = "/api/search/page/" + page;

$("#btn-pre").on("click", function() {
	if (page >= 1) {
		page--;
		fire_ajax_next();
	}
});

$("#btn-next").on("click", function() {
	totalPage = document.getElementById("totalPage").value;
	if (page < totalPage - 1) {
		page++;

		fire_ajax_next();
	}
});

function ajax_find_submit() {

	var search = {}
	search["studentName"] = $("#studentName").val();
	$("#btn-search").prop("disabled", true);
	console.log(search);
	$
			.ajax({
				type : "POST",
				url : "/api/search",
				contentType : "application/json",
				data : JSON.stringify(search),
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					var tbhtml = "";

					for (var i = 0; i < data.result.length; i++) {
						var j = i + 1;
						tbhtml += "<tr>" + "<td>"
								+ j
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentCode
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentName
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.dateOfBirth
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.address
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.averageScore
								+ "</td>"
								+ "<td>"
								+ "<form method='GET'>"
								+ "<button type='submit' class='btn btn-primary'  formaction='/edit/"
								+ data.result[i].studentInfo.infoId
								+ "' >Edit</button>"
								+ "<button type='submit' class='btn btn-danger'  formaction='/delete/"
								+ data.result[i].studentInfo.infoId
								+ "' >Delete</button>" + "</form></td>"
								+ "</tr>";
					}

					tbhtml += "<input type='hidden' id='totalPage' value="
							+ data.totalPage + ">";
					if (data.result.length > 0) {
						$('#dataList').html(tbhtml);
						$("#pagination").css("display", "block");
					} else {
						alert("Warning! không tìm thấy học sinh "
								+ data.message);
					}

					console.log("SUCCESS : ", data);
					$("#btn-search").prop("disabled", false);
				},
				error : function(e) {
					alert("Warning!Tìm kiếm null");
					console.log("AJAX ERRORRRRRRRRRRRRR: ");
					console.log("AJAX error:' + e");
					$("#btn-search").prop("disabled", false);
				}
			});

}

function fire_ajax_next() {

	$
			.ajax({
				type : "GET",
				contentType : "application/json",
				url : "/api/search/page/" + page,
				data : "",
				dataType : 'json',
				cache : false,
				timeout : 600000,
				success : function(data) {
					var tbhtml = "";

					for (var i = 0; i < data.result.length; i++) {
						var j = i + 1;
						tbhtml += "<tr>" + "<td>"
								+ j
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentCode
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentName
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.dateOfBirth
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.address
								+ "</td>"
								+ "<td>"
								+ data.result[i].studentInfo.averageScore
								+ "</td>"
								+ "<td>"
								+ "<form method='GET'>"
								+ "<button type='submit' class='btn btn-primary'  formaction='/edit/"
								+ data.result[i].studentInfo.infoId
								+ "' >Edit</button>"
								+ "<button type='submit' class='btn btn-danger'  formaction='/delete/"
								+ data.result[i].studentInfo.infoId
								+ "' >Delete</button>" + "</form></td>"
								+ "</tr>";
					}
					tbhtml += "<input type='hidden' id='totalPage' value="
							+ totalPage + ">";
					if (data.result.length > 0) {
						$('#dataList').html(tbhtml);
						$("#pagination").css("display", "block");
					} else {
						alert("Warning! không tìm thấy học sinh "
								+ data.message);
					}

					console.log("SUCCESS : ", data);
					$("#btn-search").prop("disabled", false);

				},
				error : function(e) {
					document.getElementById("pagination").style.display = 'none';
					console.log("AJAX ERRORRRRRRRRRRRRR: ");
					console.log("AJAX error:' + e");
					$("#btn-search").prop("disabled", false);

				}
			});

}
