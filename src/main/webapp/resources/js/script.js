
function addRebel(name, planet) {
	$.post("/backEndExam3/addRebel",{'name':name,'planet':planet})
	.done(function(data){
		alert(data.success);
		console.log(data.message);
	});
}

$(document).on("submit", "#rebelForm", function(e){
    e.preventDefault();
    var name = $(this).find("[name=name]").val();
    var planet = $(this).find("[name=planet]").val();
    addRebel(name, planet);
});