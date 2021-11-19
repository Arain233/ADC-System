import request from '@/utils/request'

export function verification(no) {
  return request({
    url: `http://120.76.197.109:8181/stu/findByCert_no/${no}`, // /dev-api/test
    method: 'get',
  })
}
