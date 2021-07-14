// pages/item/item.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    pageNo: 1,
    id: 1,
    limit: 7,
    totalPages: 1,
    items: []
  },

  //读取题目分类数据
  getItemList: function () {
    var t = this;
    let baseUrl = app.globalData.baseUrl;
    wx.request({
      url: baseUrl + '/question/question/list?page=' + this.data.pageNo + '&limit=' + this.data.limit + '&key=' + this.data.id,
      method: 'GET',
      header: { 'Authorization': wx.getStorageSync('token') },
      success: function (res) {
        console.log(res.data.page);
        t.setData({
          items: res.data.page,
          totalPages: res.data.page.totalPage

        })
        console.log("log ： ");
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //捕获分类列表页传递过来的分类id    
    this.setData({
      id: options.id
    })
    //获取对应分类题目列表数据
    this.getItemList()
    //读取广告轮播图数据
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
  //获取广告轮播图数据
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
  * 页面上拉触底事件的处理函数
  */
  onReachBottom: function () {
    var t = this;
    console.log(' 页面上拉触底事件的处理函数');
    //判断当前页码小于总页码，就把当前页加1，获取下一页内容
    if (t.data.pageNo < t.data.totalPages) {
      t.setData({
        pageNo: t.data.pageNo + 1
      })
      console.log(t.data.pageNo);
      let baseUrl = app.globalData.baseUrl;
      wx.request({
        url: baseUrl + '/question/question/list?page=' + this.data.pageNo + '&limit=' + this.data.limit + '&key=' + this.data.id,
        method: 'GET',
        header: { 'Authorization': wx.getStorageSync('token') },
        success: function (res) {
          //合并下一页的数据到当前集合 合并数组是把后一个数组的值依次push进前一个数组，使前一个数组发生改变
          t.data.items.list.push.apply(t.data.items.list, res.data.page.list)
          t.setData({
            items: t.data.items
          })
        }
      })
    } else {
      wx.showToast({
        title: '我是最后一页了！',
        icon: 'none',
        duration: 1000
      })
    }
  },
  //跳转到题目详情页
  getInfo: function (e) {
    var id = e.currentTarget.dataset.id
    console.log("key : " + id);

    wx.navigateTo({
      url: '../info/info?id=' + id,
    })

  },
})