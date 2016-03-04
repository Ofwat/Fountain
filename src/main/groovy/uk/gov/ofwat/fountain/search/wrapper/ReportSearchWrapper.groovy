package uk.gov.ofwat.fountain.search.wrapperimport uk.gov.ofwat.fountain.domain.Entity;import clover.gnu.cajo.utils.extra.Implements;

class ReportSearchWrapper implements Entity{
		def id	def name	def user	Date lastModified	def deleted	def publicReport	def groupId	def iplRun	def readOnly	def description	def items = []			public int getId(){		return id;	}		
}
