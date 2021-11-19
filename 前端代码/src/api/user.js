import request from '@/utils/request'

export function login(data) {
  return request({
    // url: '/ADC_System/user/login',
    url: 'http://120.76.197.109:8181/api/login',
    method: 'post',
    data
  })
}

export function register(data){
  return request({
    url:'http://120.76.197.109:8181/api/save',
    method:'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/ADC_System/user/info',
    method: 'get',
    params: { token }
  })
}

export function GetCaptcha () {
  return request({
    url: 'http://120.76.197.109:8181/api/captcha.jpg',
    method: 'get',
    responseType: 'arraybuffer' //这里特殊注明返回格式是文件流
  });
}

export function logout() {
  return request({
    url: '/ADC_System/user/logout',
    method: 'post'
  })
}
