@echo off
::当前时间
set now_time=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::备份到文件
set file_name=F:\eclipse-workspace\furesky-cms\doc\furesky-cms.sql
::MySQL
set mysqldump=D:\DevTools\mysql-5.7.26\bin
set host=127.0.0.1
set port=3306
set username=root
set password=root
set database=furesky-cms

echo.    
echo -----------------------------------------------------------------
echo                   MYSQL备份批处理
echo                      by jiandax
echo -----------------------------------------------------------------
echo                  %now_time%
echo.
echo.
echo 将备份到：
echo     %file_name%
echo.

::备份
echo 开始备份...
echo.
cd /d %mysqldump%
mysqldump --defaults-extra-file=../my.ini %database% > %file_name%
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
