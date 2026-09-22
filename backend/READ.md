Postman測試

<1>
post
URL:http://localhost:8080/api/users/login
body->raw->JSON

{
    "username": "admin",
    "password": "1234"
}

->send

取得token

<2>
GET
URL:http://localhost:8080/api/products?page=1&size=10

Authorization->Bearer Token->token內輸入上方取的的token

->send

取得

<3>
net.sf.jasperreports.extension.simple.font.families.simhei=fonts/fonts.xml

simhei
註冊這筆 font-family XML 設定時的 key,名字可以隨便取

<4>
Docker連線port設定

1521 是 Oracle 預設的資料庫連線埠

docker run -d \
  --name frog-oracle \
  -p 1521:1521 \
  container-registry.oracle.com/database/free:latest
  
<5>
Orcle DB設定
Pluggable Database（PDB）:FREEPDB1

<6>
Multipart
http傳輸格式




