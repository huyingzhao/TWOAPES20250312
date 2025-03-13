var addTimeout = undefined;
var webPxAdd = "/snippet/";

function check() {
    var title = $("#addTitleValue").val();
    var type = $("#addTypeValue").val();
    return title.trim() !== '' && type.trim() !== '';
}

function save() {
    var data = JSON.stringify($('#action').serializeJSON());
    addTimeout = setTimeout(function() {
        $("#addSaveResult").text("");
        clearTimeout(addTimeout);
    }, 3000);

    if (check()) {
        $.ajax({
            type: "post",
            url: webPxAdd + "save",
            data: data,
            dataType: "json",
            contentType: "application/json",
            success: function(result) {
                if (result.state === MESSAGE.SUCCESS) {
                    emp();
                    var count = $("#addCount");
                    count.text((parseInt(count.text()) + 1).toString());
                    $("#addSaveResult").text(result.msg + ",已成功" + (count.text()) + "次");
                    searchDetail();
                } else {
                    $("#addSaveResult").text(result.msg);
                }
            },
            error: function(result) {
                $("#addSaveResult").text(result.msg);
            }
        });
    } else {
        $("#addSaveResult").text("请检查标题或类型是否为空");
    }
}


function emp() {
    $("#addTitleValue").val("");
    $("#addDataValue").val("");
    $("#addPsValue").val("");
}
