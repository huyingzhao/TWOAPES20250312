var timeout = undefined;
var dataCopy = new ClipboardJS("#detailDataCopy");

function copyClipboard() {
    if (dataCopy !== undefined) {
        dataCopy.on('success', function (e) {
            $("#detailDataCopyText").text("复制成功");
            timeout = setTimeout(function () {
                $("#detailDataCopyText").text("");
                clearTimeout(timeout);
            }, 3000);
        });

        dataCopy.on('error', function (e) {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
        });
    }
}