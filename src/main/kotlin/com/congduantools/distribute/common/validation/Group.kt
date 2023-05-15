package com.congduantools.distribute.common.validation

import jakarta.validation.GroupSequence

/**
 * author： 马世鹏
 *
 * time: 2023/5/15
 *
 * desc:
 */

@GroupSequence(GroupA::class, GroupB::class)
interface GroupSequence {

}

interface GroupA {
}

interface GroupB {
}


