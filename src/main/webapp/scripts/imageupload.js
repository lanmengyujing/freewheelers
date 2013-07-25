$(function () {
    $('#remove').on("click", function(){
        $("#image_info").html("");
        $("#uuid_name").prop("value", "")
        $('#remove').hide();
    });
    $('#fileupload').fileupload({
        dataType: 'json',

        add: function(e, data){
            var file = data.files[0];

            function validate() {
                if(!file.type.match(/(\.|\/)(gif|jpe?g|png)$/i)){
                    $("#image_info").html("Only <strong>JPG</strong>, <strong>GIF</strong>, <strong>PNG</strong> allowed.");
                    return false;
                }

                if(file.size>1000000){
                    $("#image_info").html("Maximum file size is <strong>1 MB</strong>");
                    return false;
                }
                return true;
            }

            if(validate()){
                $("#image_info").text(data.files[0].name + "(" + (data.files[0].size/1024).toFixed(2) + "KB)")
                $('#progress').css("display","inline-block");
                data.submit();
            }
        },

        done: function (e, data) {
            $("#uuid_name").prop("value", data.result.uuidName)
            $('#progress').hide();
            $('#remove').show();
        },

        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        }
    });
});