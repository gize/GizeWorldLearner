$(document).ready(function(){
	$.getJSON(
		"/contextlist.json",
		null,
		function(data){
			$('#context').loadSelect(data);
		}
	);
});



$.fn.loadSelect = function(optionsDataArray) {
	return this.emptySelect().each(function(){
		if (this.tagName=='SELECT') {
			var selectElement = this;
			$.each(optionsDataArray,function(index,optionData){
				var option = new Option(optionData.caption,
						optionData.value);
				if ($.browser.msie) {
					selectElement.add(option);
				}
				else {
					selectElement.add(option,null);
				}
			});
		}
	});
}

function contextChanged(){
	contextname = $("#context")[0].value;
	$("#deleteLink")[0].href = "/delete_context.html?context="+contextname;
	
}

$.fn.emptySelect = function() {
	return this.each(function(){
	if (this.tagName=='SELECT') this.options.length = 0;
	});
	}