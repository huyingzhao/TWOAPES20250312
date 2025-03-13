var webPxEdit = "/snippet/";
var editTimeout = undefined;
var editDataCopy = new ClipboardJS("#editDataCopy");

function editCopyClipboard() {
    if (editDataCopy !== undefined) {
        editDataCopy.on('success', function (e) {
            $("#editDataCopyText").text("复制成功");
            editTimeout = setTimeout(function () {
                $("#editDataCopyText").text("");
                clearTimeout(editTimeout);
            }, 3000);
        });

        editDataCopy.on('error', function (e) {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
        });
    }
}

function editCheck() {
    var title = $("#editTitleValue").val();
    var type = $("#editTypeValue").val();
    return title.trim() !== '' && type.trim() !== '';
}

function editSave() {
    var data = JSON.stringify($('#editAction').serializeJSON());
    timeout = setTimeout(function () {
        $("#editSaveResult").text("");
        clearTimeout(timeout);
    }, 3000);


    if (editCheck()) {
        $.ajax({
            type: "post",
            url: webPxEdit + "save",
            data: data,
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.state === MESSAGE.SUCCESS) {
                    var data=result.data;
                    var count = $("#editCount");
                    count.text((parseInt(count.text()) + 1).toString());
                    $("#editSaveResult").text(result.msg + ",已成功" + (count.text()) + "次");

                    var index = $("#index").text();
                    $("#copyDetail"+data.id).text(data.data);

                    if (index!== "") {
                        var rows = $table.bootstrapTable('getData');
                        var row = rows[index];
                        $table.bootstrapTable('updateRow', {
                            index: index,
                            row: {
                                title: data.title,
                                type: data.type,
                                ps: data.ps,
                                data: data.data,
                                mould: data.mould,
                                state: data.state,
                                modifyDate: data.modifyDate
                            }
                        });

                    }

                } else {
                    $("#editSaveResult").text(result.msg);
                }
            },
            error: function (result) {
                $("#editSaveResult").text(result.msg);
            }
        });

        $("#edit").modal('hide');
    } else {
        $("#editSaveResult").text("请检查标题或类型是否为空");
    }
}
