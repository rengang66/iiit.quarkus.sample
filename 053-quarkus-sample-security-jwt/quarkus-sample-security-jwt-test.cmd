

mvn exec:java -Dexec.mainClass=org.acme.security.jwt.GenerateToken -Dexec.classpathScope=test -Dsmallrye.jwt.sign.key-location=privateKey.pem

http://localhost:8080/projects/permit-all

http://localhost:8080/projects/roles-allowed

http://localhost:8080/projects/roles-allowed-admin


