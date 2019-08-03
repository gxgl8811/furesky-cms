/**
 * @license Copyright (c) 2003-2019, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) { 
	config.language = 'zh-cn';
	config.skin='moono-lisa'; 
	config.tabSpaces = 4;      
    config.extraPlugins = 'codesnippet';//代码高亮
    codeSnippet_theme='atelier-forest.light';
    //config.toolbar ='Full';
    config.toolbar ='MyToolbar';
    config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;'+ config.font_names ;  
    config.toolbar_MyToolbar = [	
		[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript'] ,
		[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
		[ 'NumberedList', 'BulletedList'],
		[ 'Outdent', 'Indent'],	
		[ 'CopyFormatting', 'RemoveFormat' ],
		[ 'SelectAll','Find', 'Replace'],
		[ 'Link', 'Unlink', 'Anchor' ],
		[ 'Source','Maximize','About'],
		'/',
		['Undo', 'Redo'],
		
		[ 'Format', 'Font', 'FontSize' ],
		[ 'TextColor', 'BGColor' ],
		[ 'CodeSnippet','Image', 'Table', 'Smiley', 'SpecialChar' ]	
	];    
};  