// export function log(target, name, descriptor) {
//     // const oldValue = descriptor.value;
//     console.log('执行了装饰器');
//     console.log(target);
//     console.log(name);
//     console.log(descriptor);
//     // console.log(descriptor.value);
//     // console.log(typeof descriptor.value);
//     // descriptor.value = function() {
//     //     console.log('进来了');
//     //     console.log(this);
//     //     console.log(`Calling ${name} with`, arguments);
//     //     return oldValue.apply(this, arguments);
//     // };
//     //
//     // return descriptor;
// }
export function log() {
  return (target, prop) => {
    // const oldValue = descriptor.value;
    console.log('执行了装饰器');
    console.log(target);
    console.log(prop);
    console.log(target.hasOwnProperty(prop));
    // console.log(descriptor);
  };
}
