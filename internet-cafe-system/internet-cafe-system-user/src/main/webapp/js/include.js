$(function () {
    $.get("header.html",function (data) {
        $("#header").html(data);
    });
    $.get("lunbotu.html",function (data) {
        $("#lunbotu").html(data);
    });
});