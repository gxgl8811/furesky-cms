@echo OFF

::��ǰĿ¼
set baseDir=%~dp0
::���ݿ���
set dbName=furesky-cms
::���ݿ�����
set cfgFile=%baseDir%mysql.ini
::������SQL�ļ�
set sqlFile=%baseDir%furesky-cms.sql
::��ǰʱ��
set nowTime=%date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,2%:%time:~3,2%:%time:~6,2%

echo.    
echo -----------------------------------------------------------------
echo                   ����SQL�ļ�
echo -----------------------------------------------------------------
echo               %nowTime%
echo.
echo.
echo ����������
echo     %sqlFile%
echo.
echo ��ʼ����...
echo.
cd /d %MySQL_HOME%/bin
mysqldump --defaults-extra-file=%cfgFile% %dbName% > %sqlFile%
echo ��������...
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
