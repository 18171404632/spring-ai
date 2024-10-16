import request from 'axios'


export const getData = params => {
    console.log(params)
    console.log(`${process.env.VUE_APP_API}/ai/chat`)
    // return axios({
    //     method: 'post',
    //     url: `${process.env.VUE_APP_API}/ai/chat`,
    //     params,
    // }).then(res => res.data)
    return request.post(`${process.env.VUE_APP_API}/ai/chat`, params).then(res => res.data)
}
