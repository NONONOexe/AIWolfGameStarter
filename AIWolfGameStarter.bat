@echo off
cd /d %~dp0
java -cp lib/aiwolf-client.jar;lib/aiwolf-common.jar;lib/aiwolf-server.jar;lib/aiwolf-starter.jar;lib/aiwolf-viewer.jar;lib/jsonic-1.3.10.jar;agent/* jp.ac.maslab.ando.aiwolf.starter.AIWolfGameStarter %1 %2 %3 %4 %5 %6 %7 %8 %9
