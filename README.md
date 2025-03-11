# Setting up 
First, you need to set up a backend server
```

git clone https://github.com/maninmiddle/back
```

```
mysql -u [username] -p 4tests < 4tests.sql

```

Add the data to connect to mysql in main.js

![image](https://github.com/user-attachments/assets/72c983d7-9524-4ff3-9600-dd8998941e11)


Install dependencies

```
npm install
```

Start server
```
node main.js
```

Add server address to [BaseUrl](https://github.com/maninmiddle/a4tests/blob/master/core/common/src/main/java/com/maninmiddle/core/common/di/NetworkModule.kt)

After this you can run project

