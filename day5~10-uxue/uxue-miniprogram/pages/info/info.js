// pages/info/info.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  /**
    * 生命周期函数--监听页面加载
    */
  onLoad: function (options) {
    var id = options.id //获取值    
    var t = this;
    let baseUrl = app.globalData.baseUrl;
    wx.request({
      url: baseUrl + '/question/question/info/' + id,
      method: 'GET',
      header: { 'Authorization': wx.getStorageSync('token') },
      success: function (res) {
        // console.log("log ： "+res.data.page.list);
        t.setData({
          question: res.data.question
        })
        console.log("log ： ");
      }
    })
    //读取首页轮播图数据
    this.loadConext()
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
  //读取首页轮播图数据
  loadConext: function () {
    let baseUrl = app.globalData.baseUrl;
    var t = this;
    wx.request({
      url: baseUrl + '/context/banner/list?page=1&limit=5',
      method: 'GET',
      header: { 'Authorization': wx.getStorageSync('token') },
      success: function (res) {
        console.log("log ： " + res.data.page.list);
        t.setData({
          imgurls: res.data.page.list
        })
        console.log("log ： ");
      }
    })
  },
})