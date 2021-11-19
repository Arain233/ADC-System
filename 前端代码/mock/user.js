
const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

module.exports = [
  // // user login
  // {
  //   url: '/ADC_System/user/login',
  //   type: 'post',
  //   response: config => {
  //     const { name } = config.body
  //     const token = tokens[name]
  //
  //     // mock error
  //     if (!token) {
  //       return {
  //         code: 60204,
  //         message: 'Account and password are incorrect.'
  //       }
  //     }
  //
  //     return {
  //       code: 20000,
  //       data: token
  //     }
  //   }
  // },

  // get user info
  {
    url: '/ADC_System/user/info\.*',
    type: 'get',
    response: config => {
      const { token } = config.query

      // mock error
      if (!token) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: tokens['admin']
      }
    }
  },

  // user logout
  {
    url: '/ADC_System/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
