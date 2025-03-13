var detailTimeout = undefined;
var detailDataCopy = new ClipboardJS("#detailDataCopy");

function copyClipboard() {
    if (detailDataCopy !== undefined) {
        detailDataCopy.on('success', function (e) {
            $("#detailDataCopyText").text("复制成功");
            detailTimeout = setTimeout(function () {
                $("#detailDataCopyText").text("");
                clearTimeout(detailTimeout);
            }, 3000);
        });

        detailDataCopy.on('error', function (e) {
            console.error('Action:', e.action);
            console.error('Trigger:', e.trigger);
        });
    }
}