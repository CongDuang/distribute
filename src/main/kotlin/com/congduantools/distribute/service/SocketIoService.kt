package com.congduantools.distribute.service

/**
 * author： 马世鹏
 *
 * time: 2023/5/22
 *
 * desc:
 */
interface SocketIoService {

    /**
     * 强制下线
     */
    fun forceLogout(userId: Int)
}