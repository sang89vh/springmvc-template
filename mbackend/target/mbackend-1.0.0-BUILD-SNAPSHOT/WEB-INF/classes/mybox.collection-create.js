http(function(req) {
 // https://github.com/baasbox/baasbox/wiki/PluginApi
 // this function will handle all HTTP methods that are not overridden.
 // req contains information about the request such as:
 var method = req.method;
 var body = req.body;
 var queryString = req.queryString;
 var headers = req.headers;
 var remoteIp = req.remote; //since 0.9.2
// The return value of this method is the response that will be sent to the client (no callbacks [hell])
 return {status: /*status code*/200, 
         content: /*any json you want to return*/ {baasbox: 'rocks'}
 };
});
// you can override specific http methods
http()
 .get(function(req){
   // your get request handler
 })
 .post(function(req){
  // your post request handler
 });