$(document).ready(function(){
    $('#btn-search').click(function(){
        SearchCourse();
        return 1;
    });
});

 function SearchCourse(){
	var state = "", searchCourse = "";
    var titleCourse = $("#title-course").val;

	console.log(titleCourse);
	axios({
	    method: 'GET',
	    url: 'http://localhost:8085/apiClient/course',
	    data: null
		}).then(function (response) {
        console.log(response.data);
    	var myCourseVue = new Vue({
			el: "#showCourse",
			data: {
				courses: response.data
			}
		});
    	console.log(myCourseVue);
        localStorage.setItem("mydata", response.data)
    })
    .catch(function (error) {
    	console.log("error connect");
    });
}

function a(){
	var a = localStorage.getItem("mydata");
        console.log(a);
}