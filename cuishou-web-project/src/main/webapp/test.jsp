<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>ExtJs</title>
    <link href="ext/resources/css/ext-all.css" rel="stylesheet" type="text/css" />
    <script src="ext/ext-all.js" type="text/javascript"></script>
    <script src="ext/locale/ext-lang-zh_CN.js" type="text/javascript"></script>
    <script type="text/javascript">
        Ext.onReady(function () {
            //创建一个窗体
            var win = new Ext.Window({
                width: 300,
                height:300,
                items: [],
                modal: true,
                buttonAlign: 'center',
                buttons: [{
                    text: 'Test', id: 'btnTest', handler: function () {
                        Ext.Msg.show({
                            title: 'Question',
                            msg: 'Do you like ExtJs?',
                            fn: processResult,
                            icon: Ext.Msg.QUESTION,
                            buttons: Ext.Msg.YESNO
                        });
                    }
                }
                ]
            });
            win.show();
        });

        //询问对话框处理Handler
        function processResult(btn) {
            Ext.Msg.alert('result', btn);
        }
    </script>
</head>
<body>
    <div>
    </div>
</body>
</html>