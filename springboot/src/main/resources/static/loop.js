(function() {
'use strict';
console.log("Hello Loop!!!");

// forEach
var names = ['Name1','Name2','Name3'];

names.forEach(function(name){
	console.log(name);
});

console.log("for ..in - loop through keys of object and array (Generally, we shouldn’t use for..in for arrays)");
//for in
let person = {
	name: "Lisa",
	age: 23
}
for(let key in person){
	console.log("key: "+key+", value: "+person[key]);
}
console.log("for ..of - loop through values of array");
for(let value of names){
	console.log("value: "+value);
}
})();