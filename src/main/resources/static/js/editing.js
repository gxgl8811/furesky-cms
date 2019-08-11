/**
 * Created by jianda on 2019/4/28.
 */

//记录是否已经保存修改
var isSaved = true;

//去激活ckeditor
function inactiveEditor(editor_name) {
    if (CKEDITOR.instances[editor_name])
        CKEDITOR.instances[editor_name].destroy();
}
//激活ckeditor
function activeEditor(editor_name) {
    if (!CKEDITOR.instances[editor_name]) {
        CKEDITOR.inline(editor_name, {
            startupFocus: true
        });
    }
}
//保存文章信息
function saveArticle() {
	var articleId = $("#article_id").html();
	var title = $("#article_title").html();
    var content = $("#article_content").html();

    $.ajax({
    	url : "/furesky/cms/article/edit",
    	type : "post",    
    	contentType : "application/x-www-form-urlencoded",
    	data : {
        	"articleId": articleId,
            "title": title,
            "content": content
        },
    	dataType: "json",
    	success: function (result) {
        	alert(result.message);
        },
        error: function (err) {
        	alert("404！");
        }
    });

}

$("#edit").click(function () {
    if ("update" == $(this).attr("class")) {
        $(this).attr("class", "save");
        $("#article_title").attr("contenteditable", "true");
        $("#article_content").attr("contenteditable", "true");
        activeEditor("article_content");
        isSaved = false;
    } else {
        $(this).attr("class", "update");
        $("#article_title").removeAttr("contenteditable");
        inactiveEditor("article_title");
        $("#article_content").removeAttr("contenteditable");
        inactiveEditor("article_content");
        saveArticle();
        //window.location.reload();
        isSaved = true;
    }

});

window.onbeforeunload = function () {
    if (!isSaved) {
        return "提示：您尚有未保存的内容，离开本页将丢失编辑内容！";
    }
}
