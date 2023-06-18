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
    name: int
    person: int
    weight: int
    address: int
    isWeek: int
    saturday: int
    days: int
    month: int

app = FastAPI()

@app.post(path="/items", status_code=201)
def myiris(item: Item) :
    # pickle 파일 로딩
    with open('data.pickle', 'rb') as f:
        model = pickle.load(f)
        dicted = dict(item)
        
        name = dicted['name']
        person = dicted['person']
        weight = dicted['weight']
        address = dicted['address']
        isWeek = dicted['isWeek']
        Saturday = dicted['saturday']
        days = dicted['days']
        month = dicted['month']

        arr = np.array([[name , 
                        person , 
                        weight , 
                        address ,
                        isWeek , 
                        Saturday,
                        days , 
                        month]])
        pred = model.predict(arr)

        result = {"predict_result": pred[0] } 
        print("=======예측결과 =======>", result)

    return JSONResponse(result)


if __name__ == '__main__' :
    uvicorn.run(app, host="127.0.0.1", port=8000)