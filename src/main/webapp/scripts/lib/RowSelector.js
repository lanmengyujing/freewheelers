var RowSelector = (function(){

	function bindEvents() {
		$(document.body).bind('click', clickHandler);
	}
	
	function unbindEvents() {
		$(document.body).unbind('click', clickHandler);
	}
	
	function clickHandler(event) {
		var target = event.target;
		if ($(target).hasClass('rowSelector')) {
			toggleRow(target);
		} else if ($(target).hasClass('toggleAll') ) {
            toggleAll(target);
		}
	}

	function toggleRow(checkbox) {
		var checked = checkbox.checked;
		$(checkbox).parent().parent().find("input[type!=checkbox], select").prop("disabled", !checked);
	}
	
	function toggleAll(checkbox) {
		var checked = checkbox.checked;
        $(checkbox).parent().parent().parent().parent().find('tbody input, select').each(function(index, element){
			if ($(element).hasClass('rowSelector')) {
                $(element).prop('checked', checked)
			} else {
				$(element).prop("disabled", !checked);
			}
		});
	}
	
	return {
		bindEvents: bindEvents,
		unbindEvents: unbindEvents
	};

})();
