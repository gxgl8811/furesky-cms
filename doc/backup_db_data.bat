@echo off
::��ǰʱ��
set now_time=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::���ݵ��ļ�
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
echo                   MYSQL����������
echo                      by jiandax
echo -----------------------------------------------------------------
echo                  %now_time%
echo.
echo.
echo �����ݵ���
echo     %file_name%
echo.

::����
echo ��ʼ����...
echo.
cd /d %mysqldump%
mysqldump --defaults-extra-file=../my.ini %database% > %file_name%
echo ���ݽ���...
echo.
echo �����˳�...
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
