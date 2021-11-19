import request from '@/utils/request'
import {getToken} from '@/utils/auth'
//当使用post方法的时候，会出现请求超时的问题
//这个问题是因为mock-server中express的中间件body-parser导致的，
//表现为不带参数请求没有问题，带上参数就出现那种情况

export function importcert(code) {
  return request({
    url: `http://120.76.197.109:8181/stu/findcert/${code}`, // /dev-api/test
    method: 'get',
  })
}

export function manualcert(data) {
  return request({
    url: `http://120.76.197.109:8181/stu/cert`, // /dev-api/test
    method: 'post',
    data
  })
}
