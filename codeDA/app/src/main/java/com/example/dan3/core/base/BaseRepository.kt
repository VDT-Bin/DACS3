package com.example.dan3.core.base

import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T> safeCall(action : suspend () -> T) : AppResult<T>  {
        return try {
            AppResult.Success(action())
        } catch (e : HttpException) {
            val mess = when (e.code()) {
                400 -> "Dữ liệu không hợp lệ"
                401 -> "Không có quyền truy cập"
                403 -> "Truy cập bị từ chối"
                404 -> "Không tìm thấy tài nguyên"
                500 -> "Lỗi serve"
                else -> "Lỗi http: ${e.code()}"
            }
            AppResult.Error(mess)
        } catch (e : IOException) {
            AppResult.Error("Lỗi mạng")
        } catch (e : Exception) {
            AppResult.Error(e.message ?: "Có lỗi , chịu")
        }
    }
}
