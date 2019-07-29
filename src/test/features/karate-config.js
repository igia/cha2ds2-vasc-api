function() {
  var env = karate.env;
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    baseUrl: 'http://localhost:8054/api',
    tokenUrl: 'http://localhost:9080/auth/realms/igia/protocol/openid-connect/token',
    clientId: 'internal',
    clientSecret: 'internal'
  };

  karate.configure('connectTimeout', 50000);
  karate.configure('readTimeout', 50000);
  karate.configure('logPrettyRequest', true);
  return config;
}
