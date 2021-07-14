// pages/type/type.js
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
    wx.showTabBar({
      animation: true,
    })
    //读取全部分类数据
    this.loadAllType()
    //读取广告轮播图数据
    this.loadConext()
  },
  //读取全部分类数据
  loadAllType: function () {
    let baseUrl = app.globalData.baseUrl;
    var t = this;
    wx.request({
      url: baseUrl + '/question/type/findall',
      method: 'GET',
      header: { 'Authorization': wx.getStorageSync('token') },
      success: function (res) {
        console.log(res.data);
        t.setData({
          exams: res.data.data
        })
        console.log(res.data);
      }
    })
  },

  //读取广告轮播图
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
  //点击分类，跳转到题目列表页
  getItem: function (e) {
    var id = e.currentTarget.dataset.id
    console.log("key : " + id);
    wx.navigateTo({
      url: '../item/item?id=' + id,
    })

  },
})