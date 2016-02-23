# AIWolfGameStarter

## 概要
人狼知能のスターターです
このスターターはRoleRequestStarterの拡張です。エージェントのテストのために作成しました。このスターターでは次のことができます

 - プレイヤーの数やプレイヤーのクラスパス、役職など各種設定を設定ファイルによって設定可能
 - 繰り返し実行
 - 全ゲームのトータルでの各参加エージェントに対する結果表示

## 注意点
 - このスターターではJavaのエージェントしか使用できません
 - TCP/IP通信はしていません（いづれ対応する予定）

## 内容
 agent/ - エージェントのjarを追加するためのディレクトリ
lib/ - 人狼知能プラットフォームおよびこのスターターのjarを追加するためのディレクトリ
src/ - このスターターのソースディレクトリ
AIWolfGameStarter.bat - Windows用実行ファイル
AIWolfGameStarter.sh - MacおよびLinux用実行ファイル
LICENSE - このスターターのライセンス
README.md - このスターターの説明（この文章）
default.cfg - デフォルトの設定ファイル
player - プレイヤー定義ファイル

## 使用方法

### 準備
人狼知能プラットフォームを人狼知能公式からDLし、解凍したファイルをすべて、lib/ディレクトリに追加してください

### 実行ファイル
 - Windowsの場合
AIWolfGameStarter.batから実行してください

- MacまたはLinuxの場合
AIWolfGameStarter.shから実行してください

##テスト
実行ファイルにオプションを付けずに実行すると、SampleRoleAssignPlayer15人による100回対戦が実行されます
詳細な設定はdefault.cfgおよびplayerを参考してください

## 使用方法
1. エージェントのjarファイルをagent/に追加
2. 設定ファイルおよびプレイヤー定義ファイルを書き換え(注1,2)
3. 実行ファイルから実行(注3)

注1:設定ファイルおよびプレイヤー定義ファイルはコピーしてデフォルトの設定を残して使うこともできます
注2:設定ファイルおよびプレイヤー定義ファイルの書き方は各ファイル内にコメント文で記載されています。そちらに従ってください
注3:設定ファイルをdefault.cfgから変更する場合は-Cオプションで設定ファイルを渡してください

## あとがき
何か問題などがありましたら、issueまたは私のtwitterの方にお願いします