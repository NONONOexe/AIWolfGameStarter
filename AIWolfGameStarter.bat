@echo off
cd /d %~dp0
rem �l�T�m�\�X�^�[�^�[�N��
java -cp lib/aiwolf-starter.jar;lib/aiwolf-server.jar;agent/* jp.ac.maslab.ando.aiwolf.starter.AIWolfGameStarter %1 %2 %3 %4 %5 %6 %7 %8 %9
