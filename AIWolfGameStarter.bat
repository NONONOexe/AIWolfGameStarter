@echo off
cd /d %~dp0
rem 人狼知能スターター起動
java -cp lib/aiwolf-starter.jar;lib/aiwolf-server.jar;agent/* jp.ac.maslab.ando.aiwolf.starter.AIWolfGameStarter %1 %2 %3 %4 %5 %6 %7 %8 %9
