// pages/account/account.js
//获取应用实例
const app = getApp()

Page({
  // 获取输入账号 
  usernameInput: function (e) {
    this.setData({
      username: e.detail.value
    })
  },

  // 获取输入密码 
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },
  // 登录处理
  login: function () {
    var that = this;
    console.log('用户登录')
    if (that.data.username.length == 0 || that.data.password.length == 0) {
      wx.showToast({
        title: '账号或密码不能为空',
        icon: 'none',
        duration: 2000
      })
    } else {
      let baseUrl = app.globalData.baseUrl;
      wx.request({
        url: baseUrl + '/member/member/login',
        method: 'POST',
        header: { 'content-type': 'application/json' },
        data: {
          userName: that.data.username,
          password: that.data.password
        },
        success: function (res) {
          console.log(res);
          if (res.data.code == 0) {
            console.log("登录成功")
            //登录成功，存储token到本地存储 类似于cookie
            wx.setStorageSync('token', res.data.token);
            wx.setStorageSync('refreshToken', res.data.refreshToken);
            //显示登录成功提示，跳转到题库分类页面
            wx.showToast({
              title: "登录成功",
              icon: 'none',
              duration: 1000,
              success: function () {
                console.log('haha');
                setTimeout(function () {
                  //要延时执行的代码      
                  wx.switchTab({
                    url: '../type/type'
                  })
                }, 1000) //延迟时间      
              }
            })

          } else {
            console.log(res.data.msg)
            wx.showToast({
              title: res.data.msg,
              icon: 'none',
              duration: 2000
            })
          }


        }
      })
    }
  },

  getUserProfile(e) {
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res.userInfo)
        //设置全局属性
        app.globalData.userInfo = res.userInfo
        app.globalData.hasUserInfo = true
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        //跳转到注册页码
        this.register()
      }
    })
  },

  //注册跳转
  register: function () {
    //跳转到注册页面
    wx.redirectTo({
      url: '../register/register',
    })
  },
  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    // 生命周期函数--监听页面显示
    //判断如果登录token不存在就隐藏tabBar   
    if (!wx.getStorageSync('token')) {
      wx.hideTabBar({})
    }
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})