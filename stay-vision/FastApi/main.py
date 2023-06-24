# Package Import
from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse

import pickle
import numpy as np
import pandas as pd

# Request Body
class Item(BaseModel) :
    platform: str
    name: str
    address: str
    maxPerson: int
    roomInfo: str
    checkIn: str
    checkOut: str

app = FastAPI()

@app.post(path="/items", status_code=201)
def myiris(item: Item) :
    # pickle 파일 로딩
    with open('data.pickle', 'rb') as f:
        platform_dic = {'네이버': 0, '떠나요': 1}

        name_dic = {'더원풀빌라': 0,
         '루나키즈풀빌라': 1,
         '바오키즈풀빌라': 2,
         '뷰델카라반펜션': 3,
         '빵빵키즈풀빌라': 4,
         '삐삐키즈풀빌라&카라반글램핑': 5,
         '솜솜키즈풀빌라': 6,
         '신영펜션': 7,
         '원더풀글램핑앤카라반': 8,
         '이오스풀빌라AD': 9,
         '조이키즈풀빌라': 10,
         '초코키즈풀빌라': 11,
         '츠키노야료칸풀빌라': 12,
         '키즈포레풀빌라': 13,
         '평창리버힐': 14,
         '화이트스톤풀빌라': 15}
         
        address_dic = {'강원 평창군': 0,
         '강원 홍천군': 1,
         '경기 가평군': 2,
         '경기 안산시': 3,
         '경기 포천시': 4,
         '경남 거제시': 5,
         '경남 밀양시': 6,
         '경북 경주시': 7}
        weight = {
         '개별바베큐': 10,
         '침대방': 15,
         '독채': 15,
         '오션뷰': 10,
         '월풀/스파': 20,
         '풀빌라': 20,
         '얼리 체크인': 5,
         '레이트 체크아웃': 5}
            
        model = pickle.load(f)
        dicted = dict(item)
        
        platform = dicted['platform']
        name = dicted['name']
        address = dicted['address']
        maxPerson = dicted['maxPerson']
        roomInfo = dicted['roomInfo']
        checkIn = dicted['checkIn']
        checkOut = dicted['checkOut']

        df = pd.DataFrame({'platform':[platform], 'name':[name], 'address':[address],'maxPerson':[maxPerson], 'checkIn':[checkIn], 'checkOut':[checkOut]})
        df['checkIn'] = pd.to_datetime(df['checkIn'])
        df['checkOut'] = pd.to_datetime(df['checkOut'])
        df['week'] = ((df['checkIn'].dt.dayofweek >= 5) | (df['checkOut'].dt.dayofweek >= 5)).astype(int)
        df['saturday'] = (df['checkOut'].dt.dayofweek == 5).astype(int)
        day = df['checkOut'] - df['checkIn']
        df['days'] = day.dt.days+1
        df['month'] = df['checkIn'].dt.month

        df['roomDetail'] = sum([ weight[j] for j in roomInfo.split(',') if j in weight])
        df['address'] = ' '.join(df['address'][0].split()[:2]) 
        df['address'] = address_dic[df['address'][0]]
        df['platform'] = platform_dic[df['platform'][0]]
        df['name'] = name_dic[df['name'][0]]
        df = df[['platform','name','address','maxPerson','week','saturday','days','month','roomDetail']]

        pred = model.predict(df)

        result = {"predict_result": int(pred[0])} 
        print("=======예측결과 =======>", result)

    return JSONResponse(result)


if __name__ == '__main__' :
    uvicorn.run(app, host="127.0.0.1", port=8000)