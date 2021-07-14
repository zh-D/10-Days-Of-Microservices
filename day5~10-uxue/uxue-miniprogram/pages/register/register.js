const app = getApp()
// pages/register/register.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //设置性别选择数据
    sex: [{
      id: 1,
      value: '男'
    }, {
      id: 0,
      value: '女'
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.userInfo) {
      console.log(app.globalData.userInfo)
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    }
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

  },

  /**
   * 
   * 捕获单选框的选中值
   */
  radioChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    //设置性别值给本地变量
    this.setData({
      gender: e.detail.value
    })
  },
  formSubmit: function (e) {
    console.log('表单被提交')
    var that = e.detail.value
    let baseUrl = app.globalData.baseUrl;
    wx.request({
      url: baseUrl + '/member/member/save',
      method: 'POST',
      header: { 'content-type': 'application/json' },
      data: {
        userName: that.userName,
        password: that.password,
        nickname: that.nickname,
        phone: that.phone,
        email: that.email,
        gender: this.data.gender,//从本地变量获取性别
        birth: that.birth,
        city: that.city
      },
      success: function (res) {
        console.log(res);
        if (res.data.code == 0) {
          console.log("注册成功")
          wx.showToast({
            title: "注册成功",
            icon: 'none',
            duration: 1000,
            success: function () {

              console.log('haha');

              setTimeout(function () {

                //要延时执行的代码

                wx.switchTab({

                  url: '../account/account'

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

})