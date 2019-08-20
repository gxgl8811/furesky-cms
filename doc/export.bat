@echo OFF

::当前目录
set baseDir=%~dp0
::数据库名
set dbName=furesky-cms
::数据库配置
set cfgFile=%baseDir%mysql.ini
::导出的SQL文件
set sqlFile=%baseDir%furesky-cms.sql
::当前时间
set nowTime=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%

echo.    
echo -----------------------------------------------------------------
echo                   导出SQL文件
echo -----------------------------------------------------------------
echo               %nowTime%
echo.
echo.
echo 将导出到：
echo     %sqlFile%
echo.
echo 开始导出...
echo.
cd /d %MySQL_HOME%/bin
mysqldump --defaults-extra-file=%cfgFile% %dbName% > %sqlFile%
echo 导出结束...
echo.
echo 即将退出...
ping -n 2 127.0.0.1>nul
echo.
echo .....3.....
ping -n 2 127.0.0.1>nul
echo.
echo .....2.....
ping -n 2 127.0.0.1>nul
echo.
echo .....1.....
ping -n 2 127.0.0.1>nul
