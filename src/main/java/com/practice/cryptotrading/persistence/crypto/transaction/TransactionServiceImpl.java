package com.practice.cryptotrading.persistence.crypto.transaction;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author Sin Yee
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	TransactionRepository transactionRepository;

	private final String ORDER_DIRECTION_ASC = "ASC";
	private final String ORDER_DIRECTION_DESC = "DESC";

	private final int PAGE_SIZE_MAX = 50;
	private final int PAGE_SIZE_DEFAULT = 10;

	private final String ID = "id";
	private final String CREATED_AT = "createdAt";
	private final String ORDER_TYPE = "orderType";

	@Override
	public Page<Transaction> listAll(Long userId, int page, int pageSize, String orderBy, String orderDirection) {
		if (!StringUtils.equalsAnyIgnoreCase(orderBy, ID, CREATED_AT, ORDER_TYPE)) {
			orderBy = CREATED_AT;
		}
		if (!StringUtils.equalsAnyIgnoreCase(orderDirection, ORDER_DIRECTION_ASC, ORDER_DIRECTION_DESC)) {
			orderDirection = ORDER_DIRECTION_DESC;
		}

		Sort sort = null;
		if (orderDirection.equalsIgnoreCase(ORDER_DIRECTION_ASC)) {
			sort = Sort.by(orderBy).ascending();
		} else {
			sort = Sort.by(orderBy).descending();
		}

		if (page < 1) {
			page = 1;
		}
		if (pageSize < 0) {
			pageSize = PAGE_SIZE_DEFAULT;
		} else if (pageSize >= PAGE_SIZE_MAX) {
			pageSize = PAGE_SIZE_MAX;
		}
		Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
		return transactionRepository.findByUserId(userId, pageable);
	}

	@Override
	public Transaction add(Transaction transaction) {
		if (transaction != null) {
			entityManager.persist(transaction);
			return transaction;
		}
		return null;
	}

}
