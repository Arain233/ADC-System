import request from '@/utils/request'

export function search(code) {
  return request({
    url: `http://120.76.197.109:8181/stu/cert/${code}`, // /dev-api/test
    method: 'get',
    // headers: {'Content-Type': 'application/pdf'},
    // responseType: 'blob',
    // transformRequest: function (obj) {
    //   var str = [];
    //   for (var p in obj)
    //     str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
    //   return str.join("&");
    // },
  })
}
