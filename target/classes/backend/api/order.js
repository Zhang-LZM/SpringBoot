// 查询列表页接口
const getOrderDetailPage = (params) => {
  return $axios({
    url: '/order/page',
    method: 'get',
    params
  })
}

// 查看接口
const queryOrderDetailById = (id) => {
  return $axios({
    url: `/orderDetail/${id}`,
    method: 'get'
  })
}

// 取消，派送，完成接口
const editOrderDetail = (params, id) => {
  let url = '';
  if (params.status === 3) {
    url = `/order/dispatch/${id}`;
  } else if (params.status === 4) {
    url = `/order/complete/${id}`;
  }
  return $axios({
    url,
    method: 'put',
    data: { ...params }
  })
}
