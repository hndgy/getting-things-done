export default function authHeader(contentType){
    const accessToken = localStorage.getItem("accessToken");
    var res = {};
    if(accessToken){
        res[ 'Authorization'] = 'Bearer ' + accessToken
    }
    if(!contentType){
        res[ 'Content-Type'] = 'application/json';
    }else{
        res[ 'Content-Type'] = contentType;
    }


    return res;
}
