@echo off
::��ǰʱ��
set nowTime=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%
::��ǰĿ¼
set baseDir=%~dp0
::MySQL���ݿ�
set database=furesky-cms
::�����ļ���
set fileName=%baseDir%furesky-cms.sql


echo.    
echo -----------------------------------------------------------------
echo                   MYSQL����������
echo                      by jiandax
echo -----------------------------------------------------------------
echo                  %nowTime%
echo.
echo.
echo �����ݵ���
echo     %fileName%
echo.

::����
echo ��ʼ����...
echo.
cd /d %MySQL_HOME%/bin
mysqldump --defaults-extra-file=%baseDir%mysql.ini %database% > %fileName%
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
