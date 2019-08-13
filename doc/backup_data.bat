@echo off
::当前时间
set nowTime=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::当前目录
set baseDir=%~dp0
::MySQL数据库
set database=furesky-cms
::备份文件到
set fileName=%baseDir%furesky-cms.sql


echo.    
echo -----------------------------------------------------------------
echo                   MYSQL备份批处理
echo                      by jiandax
echo -----------------------------------------------------------------
echo                  %nowTime%
echo.
echo.
echo 将备份到：
echo     %fileName%
echo.

::备份
echo 开始备份...
echo.
cd /d %MySQL_HOME%/bin
mysqldump --defaults-extra-file=%baseDir%mysql.ini %database% > %fileName%
echo 备份结束...
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
