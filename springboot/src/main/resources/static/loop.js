(function() {
'use strict';
console.log("Hello Loop!!!");

// forEach
var names = ['Name1','Name2','Name3'];

names.forEach(function(name){
	console.log(name);
});


//for in
let person = {
	name: "Lisa",
	age: 23
}
for(let key in person){
	console.log("key: "+key+", value: "+person[key]);
}
})();